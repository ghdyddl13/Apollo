package inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ReloadInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result = true;
		System.out.println("인터셉터 들어옴");
		System.out.println(request.getSession().getAttribute("location"));
		System.out.println(request.getSession().getAttribute("sid"));
		System.out.println(request.getSession().getAttribute("pid"));
		if (request.getSession().getAttribute("location") != null) {
			response.sendRedirect(request.getContextPath() + request.getSession().getAttribute("location"));
			result = false;
		}

		return result;
	}

}
