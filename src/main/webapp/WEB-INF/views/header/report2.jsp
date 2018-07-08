<%@page import="org.apache.poi.hssf.util.HSSFColor"%>

<%@ page pageEncoding = "EUC-KR" %>

<%@ page import = "java.io.*, org.apache.poi.hssf.usermodel.*, org.apache.poi.poifs.filesystem.POIFSFileSystem" %>

 

<%

 try{

        //하나의 엑셀 파일에 해당하는 Excel Workbook 생성

       HSSFWorkbook workbook = new HSSFWorkbook();

        //Sheet 생성

       HSSFSheet sheet = workbook.createSheet("first sheet");

        //생성한 Sheet의 인덱스, 이름을 설정

       workbook.setSheetName(0, "시트1");

        //Sheet 행을 하나 만든다. (rownum 인덱스 지정)

       HSSFRow row = sheet.createRow((short)0);

        //Sheet 셀을 만든다. (column 인덱스 지정)

        //행에 셀을 3개 만든 후 값 대입

       HSSFCell cell1 = row.createCell((short)0);

        //셀의 폰트 지정하는 클래스

       HSSFFont font = workbook.createFont();

        

        //셀의 스타일 지정 클래스

       HSSFCellStyle cellStyle = workbook.createCellStyle();

       cellStyle.setFillBackgroundColor(HSSFColor.BLUE.index2);

       cell1.setCellValue("number1");

       cell1.setCellStyle(cellStyle);

       font.setFontHeightInPoints((short)14);

       font.setFontName("바탕체");

       font.setColor((short)10);

      

       HSSFCell cell2 = row.createCell((short)1);

       cell2.setCellValue("number2");

      

       HSSFRow row1 = sheet.createRow((short)1);

       HSSFCell cell3 = row1.createCell((short)0);

       cell3.setCellValue("number3");

      

       FileOutputStream fileOutput = new FileOutputStream("C:\\Apollo_Reports\\abcd.xls");

 

       workbook.write(fileOutput);

       fileOutput.close();

 

       out.println("Excel File 생성 OK");

      

       //엑셀 파일 불러오기

       POIFSFileSystem fileInput = new POIFSFileSystem(new FileInputStream("C:\\Apollo_Reports\\abcd.xls"));

 

       HSSFWorkbook workbook2 = new HSSFWorkbook(fileInput);

       HSSFSheet sheet2 = workbook2.getSheetAt(0); //시트 생성

       HSSFRow row2 = sheet2.getRow(0); // 행 생성

 

       for(int j=0; j<=sheet2.getLastRowNum(); j++){//행 수만큼

             row2 = sheet2.getRow(j);

             for(int i=0; i<= row2.getLastCellNum()-1; i++){ // 열 수만큼

               HSSFCell cell4 = row2.getCell((short)i);

               System.out.println(cell4); //값 출력

             } 

       }

      

 }catch(Exception e){

       out.println(e);

 }

%>

 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

</head>

<body>

 

</body>

</html>

