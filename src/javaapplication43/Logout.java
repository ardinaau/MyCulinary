/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication43;

import javax.swing.JFrame;

/**
 *
 * @author ardina
 */
public class Logout {
       public static void logOut(JFrame context, LoginAdmin loginScreen){
        LoginSession.isLoggedIn = false;
        context.setVisible(false);
        loginScreen.setVisible(true);
    }
}
