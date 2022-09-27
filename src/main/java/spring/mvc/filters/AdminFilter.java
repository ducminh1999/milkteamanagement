package spring.mvc.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest) request);
		HttpServletResponse resp = ((HttpServletResponse) response);
		System.out.println("Filter Info:\t" + new Date() + "\t" + req.getContextPath() + req.getServletPath());
		HttpSession session = req.getSession();
		Object username = session.getAttribute("loginUser");
		if (username != null) {
			chain.doFilter(request, response);
			return;
		}

		resp.sendRedirect(req.getContextPath() + "/loginAdmin");
		return;
	}

}
