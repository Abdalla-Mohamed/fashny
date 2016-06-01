/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Abdalla
 */
@WebFilter(filterName = "ClientPagesFilter", urlPatterns = {"/faces/clientpages/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class ClientPagesFilter implements Filter {
    
    
    private FilterConfig filterConfig = null;
    
    public ClientPagesFilter() {
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
            chain.doFilter(request, response);
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
