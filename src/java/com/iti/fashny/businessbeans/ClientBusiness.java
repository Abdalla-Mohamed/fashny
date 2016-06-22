/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.interfaces.Commens;
import java.util.List;
import java.util.ArrayList;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Bakar M.M.R
 */
public class ClientBusiness implements Commens<Client> {

    @Override
    public Client login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Client t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        try {
            daoFactory.beginTransaction();
            clientFacade.create(t);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(Client t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        try {

            daoFactory.beginTransaction();
            clientFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Client> view() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Client> clientResults = new ArrayList<>();
        try {
            ClientFacade clientFacade = daoFactory.getClientDoa();
            clientResults = clientFacade.findAll();
            for (Client client : clientResults) {
                System.out.println(client.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            daoFactory.close();
        }
        return clientResults;
    }

    @Override
    public List<Client> searchByExample(Client t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client showSpecificInfo(int id) {
        Client client = new Client();
        DaoFactory daoFactory = new DaoFactory();

        try {
            ClientFacade clientFacade = daoFactory.getClientDoa();
            client = clientFacade.find(id);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            daoFactory.close();
        }
        return client;
    }

    public void addImageToClient(UploadedFile image, Client client) {
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        ResouceFacade resouceDoa = daoFactory.getResouceDoa();
        System.out.println("~~~~~~~~~~~~~~~~~~~  " + image.getFileName() + " ~~~~~~~~~~~~~~~");
        try {
            daoFactory.beginTransaction();

            Integer clientId = client.getId();

            UploadImage uploadImage = new UploadImage();
            uploadImage.setFile(image);
            uploadImage.forClient("" + clientId);
            String filePath = uploadImage.handleFileUpload();

            Resouce resouce = new Resouce(null, filePath);

            if (client.getProfilePic() != null) {
                resouceDoa.remove(client.getProfilePic());
            }
            resouceDoa.create(resouce);

            Client find = clientFacade.find(clientId);
            find.setProfilePic(resouce);
            client.setProfilePic(resouce);

            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }

    }

}
