package kr.or.apollo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/index.htm")
	public String main() {
		return "home.login";
	}
	
	@RequestMapping("/join.htm")
	public String join() {
		return "home.join";
	}
	
	@RequestMapping("/login.htm")
	public String login() {
		System.out.println("dd");
		return "project.information";
	}
	
	@RequestMapping("/file.htm")
	public String projectFilelist() {
		return "project.files";
	}
	
	@RequestMapping("/information.htm")
	public String projectInformation() {
		return "project.information";
	}
	
	@RequestMapping("/table.htm")
	public String projectTable() {
		return "project.table";
	}
	
	@RequestMapping("/inbox.htm")
	public String inbox() {
		System.out.println("inbox");
		return "header.inbox";
	}
	
	@RequestMapping("/myWork.htm")
	public String myWork() {
		return "header.myWork";
	}
	
	@RequestMapping("/report.htm")
	public String report() {
		return "header.report";
	}
	
	@RequestMapping("/starredTask.htm")
	public String starredTask() {
		return "header.starredTask";
	}
	
	@RequestMapping("/stream.htm")
	public String stream() {
		return "header.stream";
	}
	@RequestMapping("/board.htm")
    public String board() {
        
        return "step.board";
    }
    
    @RequestMapping("/list.htm")
    public String list() {
        
        return "step.list";
    }
    
    @RequestMapping("/timeline.htm")
    public String timeLine() {
        
        return "step.timeline";
    }
    
    @RequestMapping("/workload.htm")
    public String workLoad() {
        
        return "step.workload";
    }
	
}
