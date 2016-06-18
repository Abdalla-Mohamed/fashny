/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.filters;

import com.iti.fashny.assets.LoginAccount;
import com.iti.fashny.assets.Role;
import com.iti.fashny.interfaces.Guest;
import com.iti.fashny.managedbeans.LoginManagedBean;
import java.io.IOException;
import javax.servlet.DispatcherType;
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

/**
 *
 * @author Abdalla
 */
@WebFilter(filterName = "PartnerPagesFilter", urlPatterns = {"/faces/partnerpages/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class PartnerPagesFilter implements Filter {

    private FilterConfig filterConfig = null;

    public PartnerPagesFilter() {
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        Throwable problem = null;
        try {
            System.out.println("check partner logging");
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            LoginManagedBean loginManagedBean = (session != null) ? (LoginManagedBean) session.getAttribute("login") : null;

            if (loginManagedBean != null && loginManagedBean.isLogged()) {
                LoginAccount loginAccount = loginManagedBean.getLoginAccount();

                if (loginAccount.getRole().equals(Role.Partner)) {
                    chain.doFilter(request, response);

                }else {

                    ((HttpServletResponse) response).sendRedirect(Guest.DENIED_PAGE_URL);

                }
            } else {
                ((HttpServletResponse) response).sendRedirect(Guest.LOGGINED_PAGE_URL);
            }
        } catch (Throwable t) {

            problem = t;
            t.printStackTrace();
        }

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }

        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ClientPagesFilter()");
        }
        StringBuffer sb = new StringBuffer("ClientPagesFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

}
