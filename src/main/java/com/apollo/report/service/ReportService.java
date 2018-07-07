package com.apollo.report.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.apollo.member.dao.MemberDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.TaskDTO;


@Service
public class ReportService {

	@Autowired
	private SqlSession session;
	
		public ArrayList<ProjectDTO> gerUserProjects(String mid){

			System.out.println("gerUserProjects 서비스 실행");
			System.out.println("mid 테스트 : " + mid);
			
			MemberDAO dao = session.getMapper(MemberDAO.class);
			ArrayList<ProjectDTO> list = new ArrayList();
			list = dao.gerUserProjects(mid);
			
			return list;
		}
	
	
		public void writeData(int pid, String report_kind, String report_title, HttpServletResponse response) throws Exception {

			String filePath = "";
			String filename = "";
			try {

			   filePath = "C:\\Apollo_Reports\\"; // file 생성 위치 
			   filename = report_title + ".xls"; // 생성될 파일 이름
				
			   HSSFWorkbook wb = setExcel(pid, report_kind, filename);
		       OutputStream out = new BufferedOutputStream(response.getOutputStream());
             
		       ////// 서버측 다운로드 설정
		       File file = new File(filePath+filename);
		       FileOutputStream fos = null;
		       
		       response.reset();
		       response.setHeader("Content-Disposition", "attachment;filename="+filename);
		       response.setContentType("application/vnd.ms-excel");
		       response.setHeader("Content-Transfer-Encoding", "binary");
		     
		        fos = new FileOutputStream(file);
		        wb.write(fos);
		        
		        wb.write(out);
		        out.flush();
				   
				if(out != null) out.close();
				   
			} catch (Exception e) {
				e.printStackTrace();
				}
			
		}

		private FileOutputStream setFile(String filePath, String filename) throws IOException {

			// 엑셀 파일 생성
			// 디렉토리 없으면 생성.

			File fDir = new File(filePath);
			if (!fDir.exists()) {
				fDir.mkdirs();
			}
			
			FileOutputStream fout = new FileOutputStream(filePath + filename);
			return fout;
		}

		private HSSFWorkbook setExcel(int pid, String report_kind, String report_title) throws IOException {

			// 엑셀 파일 생성
			HSSFWorkbook wb = new HSSFWorkbook();

			// 쉬트 및 폰트 지정
			HSSFSheet sht = wb.createSheet(report_title);
			sht.setGridsPrinted(true);
			sht.setFitToPage(true);
			sht.setDisplayGuts(true);
			
			HSSFRow row = null;
			HSSFCell cell = null;

			// 쉬트 이름 주기
			wb.setSheetName(0, report_title);

			// 데이터 생성
			////////////////////////////////////////////////////
			
			System.out.println(pid);
			System.out.println(report_kind);
			
			TaskDAO taskdao = session.getMapper(TaskDAO.class);
			ArrayList<TaskDTO> tasklist = new ArrayList();
			
			if(report_kind.equals("report_progress")) {
				tasklist = taskdao.getOnProgressTasklist(pid);
			}
			else if(report_kind.equals("report_status")) {
				tasklist = taskdao.getTasklistByPidOrderByStatus(pid);
			}
			else if(report_kind.equals("report_expired")) {
				tasklist = taskdao.getExpiredTasklist(pid);
			}
			else if(report_kind.equals("report_drawnear")) {
				tasklist = taskdao.getDrawnearTasklist(pid);
			}
			else if(report_kind.equals("report_unassigned")) {
				tasklist = taskdao.getUnassingedTasklist(pid);
			}
			System.out.println("DB에서 데이터 가져옴(성공)");
			////////////////////////////////////////////////////
			
			// 제목 줄 생성
			ArrayList<String> title = new ArrayList();
			if(report_kind.equals("report_status")) {
				
				title.add("Task이름");
				title.add("시작일");
				title.add("종료일");
				title.add("상세정보");
				title.add("상태");
				
			} else {
				
				title.add("Task이름");
				title.add("시작일");
				title.add("종료일");
				title.add("상세정보");
				
			}
			
			// row 1 table start
			row = sht.createRow((short) 1);
			row.setHeight((short) 1000); // 칼럼 높이

			// ========== title - first row ========================
			for (int i = 0; i < title.size(); i++) {
				if((i == 1) || (i ==2 )) {
					sht.setColumnWidth(i, 10 * 500); // Column 넓이 설정
				}
				else if(i==3) {
					sht.setColumnWidth(i, 10 * 1500); // Column 넓이 설정
				}
				else {
					sht.setColumnWidth(i, 10 * 800); // Column 넓이 설정
				}
				cell = row.createCell(i);
				cell.setCellValue(new HSSFRichTextString(title.get(i)));

				cell.setCellStyle(getTitleStyle(wb));
			}

			// =========== Table Contents ===================
			
			if(report_kind.equals("report_status")) {
				
				for(int k = 0; k < tasklist.size(); k++) {
					
					TaskDTO dto = tasklist.get(k);
					
					String tname = dto.getTname();
					String sday = dto.getSday();
					String eday = dto.getEday();
					String detail = dto.getDetail();
					String tstatus = dto.getTstatus();
					
					String[] contents = {tname, sday, eday, detail, tstatus};
					
					row = sht.createRow(k + 2);
					row.setHeight((short) 500); // 칼럼 높이

					for (int i = 0; i < contents.length; i++) {
						
						if((i == 1) || (i ==2 )) {
							sht.setColumnWidth(i, 10 * 500);
						}
						else if(i==3) {
							sht.setColumnWidth(i, 10 * 1500);
						}
						else {
							sht.setColumnWidth(i, 10 * 800);
						}
						cell = row.createCell((i));

						cell.setCellValue(new HSSFRichTextString(contents[i]));
						cell.setCellStyle(getTextStyle(wb));
					} // end for - 이번 row td setting
					
				} // end for - 전체 테이블 data setting (1 회전당 1 row)
				
			} else {
				
				for(int k = 0; k < tasklist.size(); k++) {
					
					TaskDTO dto = tasklist.get(k);
					
					String tname = dto.getTname();
					String sday = dto.getSday();
					String eday = dto.getEday();
					String detail = dto.getDetail();
					
					String[] contents = {tname, sday, eday, detail};
					
					row = sht.createRow(k + 2);
					row.setHeight((short) 500); // 칼럼 높이

					for (int i = 0; i < contents.length; i++) {
						
						if((i == 1) || (i ==2 )) {
							sht.setColumnWidth(i, 10 * 500);
						}
						else if(i==3) {
							sht.setColumnWidth(i, 10 * 1500);
						}
						else {
							sht.setColumnWidth(i, 10 * 800);
						}
						cell = row.createCell((i));

						cell.setCellValue(new HSSFRichTextString(contents[i]));
						cell.setCellStyle(getTextStyle(wb));
					} // end for - 이번 row td setting
					
				} // end for - 전체 테이블 data setting (1 회전당 1 row)
			}
			
			// 출력설정
			HSSFPrintSetup hps = sht.getPrintSetup();

			// 용지설정
			hps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

			// 출력방향설정
			hps.setLandscape(false);

			// 출력비율설정
			hps.setScale((short) 100);

			// footer에 페이지번호 설정
			HSSFFooter footer = sht.getFooter();
			footer.setCenter(HSSFFooter.page() + "/" + HSSFFooter.numPages());

			// 쉬트 여백 설정
			sht.setMargin(HSSFSheet.TopMargin, 0.6);
			sht.setMargin(HSSFSheet.BottomMargin, 0.6);
			sht.setMargin(HSSFSheet.LeftMargin, 0.6);
			sht.setMargin(HSSFSheet.RightMargin, 0.6);

			return wb;

		}

		/*
		 * @@@@@ Font 설정 Method @@@@@
		 */

		private HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {

			// 제목 폰트
			HSSFFont hf = wb.createFont();
			hf.setFontHeightInPoints((short) 8);
			hf.setColor((short) HSSFColor.BLACK.index);
			hf.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// Header style setting
			HSSFCellStyle hcs = wb.createCellStyle();
			hcs.setFont(hf);
			hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// set border style
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THICK);
			hcs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderTop(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			// set color
			hcs.setFillBackgroundColor((short) HSSFColor.WHITE.index);
			hcs.setFillForegroundColor((short) HSSFColor.PALE_BLUE.index);
			hcs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			hcs.setLocked(true);
			hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			return hcs;

		}

		/*
		 * @@@@@ Font 설정 Method @@@@@
		 */
		private HSSFCellStyle getTextStyle(HSSFWorkbook wb) {

			HSSFFont hf = wb.createFont();
			hf.setFontHeightInPoints((short) 8);
			hf.setColor((short) HSSFColor.BLACK.index);

			HSSFCellStyle hcs = wb.createCellStyle();
			hcs.setFont(hf);
			hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);


			// set border style
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THICK);
			hcs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderTop(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THIN);


			// set color
			hcs.setFillBackgroundColor((short) HSSFColor.WHITE.index);
			hcs.setFillForegroundColor((short) HSSFColor.WHITE.index);
			hcs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			hcs.setLocked(true);
			hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			return hcs;
		}
	}
