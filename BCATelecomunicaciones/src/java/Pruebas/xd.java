/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;


import DAO.ClientesJpaController;
import DTO.Planes;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Retr0
 */
public class xd {

    public static void main(String[] args) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
        ClientesJpaController daoClient= new ClientesJpaController(emf);
        for (int i = 0; i < daoClient.getClientesCount(); i++) {
            System.out.println(daoClient.findClientesEntities().get(i).getNombre());
            
        }
    }
}
