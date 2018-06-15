package inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ExInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		try {
			System.out.println("Exception : " + ex.getMessage());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		long start = System.currentTimeMillis();
		request.getSession().setAttribute("start", start);
		System.out.println("start : " + start);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		System.out.println("postHandle");
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		System.out.println(">" + request.getSession().getAttribute("mid") + "<");
		boolean result = false;
		
		if (request.getSession().getAttribute("mid") == null) {
			System.out.println("여기");
			response.sendRedirect(request.getContextPath() + "/index.htm");
		}

		return result;
	}

}
