package com.iti.fashny.exceptions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bakar M.M.R
 */
public class NotConfirmAccountException extends Exception {

    /**
     * Creates a new instance of <code>AlreadyExsist</code> without detail
     * message.
     */
    public NotConfirmAccountException() {
        super("this account need confirmation from admin");
    }

    /**
     * Constructs an instance of <code>AlreadyExsist</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NotConfirmAccountException(String msg) {
        super(msg);
    }
}
