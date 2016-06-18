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
public class DeletedAccountException extends Exception {

    /**
     * Creates a new instance of <code>AlreadyExsist</code> without detail
     * message.
     */
    public DeletedAccountException() {
        super("this account is blocked");
    }

    /**
     * Constructs an instance of <code>AlreadyExsist</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DeletedAccountException(String msg) {
        super(msg);
    }
}
