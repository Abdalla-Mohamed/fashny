/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

import com.iti.fashny.businessbeans.PartnerBusiness;
import com.iti.fashny.entities.Partener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdalla
 */
public class Tst {

    public Tst() {

    }

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("notify");
        logger.fine("new companies");
        System.out.println(logger);
//                logger.
//        for (Governorates governorates : Governorates.values()) {
//            System.out.println(governorates.getGovName());
//        }

        PartnerBusiness business = new PartnerBusiness();
        try {
            List<Partener> list = business.view();
            for (Partener partener : list) {
                System.out.println(partener.getName());

            }
        } catch (Exception ex) {
            Logger.getLogger(Tst.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
