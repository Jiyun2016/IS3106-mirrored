package web.filter;

import entity.AdminEntity;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AdminSecurityFilter", urlPatterns = {"/*"})

public class AdminSecurityFilter implements Filter {

    FilterConfig filterConfig;

    private static final String CONTEXT_ROOT = "/CareTaskPro-war/AdminWeb";

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpServletRequest.getSession(true);
        String requestServletPath = httpServletRequest.getServletPath();

        if (httpSession.getAttribute("isLogin") == null) {
            httpSession.setAttribute("isLogin", false);
        }

        Boolean isLogin = (Boolean) httpSession.getAttribute("isLogin");

        if (!excludeLoginCheck(requestServletPath)) {
            if (isLogin != true) {
                httpServletResponse.sendRedirect(CONTEXT_ROOT + "/adminHome.xhtml");

            } 
            else {
                   chain.doFilter(request, response);
            }
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }

    private Boolean excludeLoginCheck(String path) {
        
        System.err.println("******* excludeLoginCheck: " + path);
        
        if (path.equals("/AdminWeb/adminHome.xhtml")
                || path.equals("/AdminWeb/adminAbout.xhtml")
           //     || path.equals(CONTEXT_ROOT + "/help.xhtml")
           //     || path.equals(CONTEXT_ROOT+ "/error.xhtml")
           //     || path.startsWith("/images")
           //     || path.startsWith("/javax.faces.resource")
                ) 
                {
            return true;
        } else {
            return false;
        }
    }

}
