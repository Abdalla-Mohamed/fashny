/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

*/
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Service;
import com.iti.fashny.entities.ServiceCategorey;
import java.util.List;

/**
 *
 * @author Hosam
 */
public interface PartenerInter {

    public void addServiceCategory(ServiceCategorey serviceCategory);

    public void updateServiceCategory(ServiceCategorey serviceCategory);

    public void activateServiceCategory(ServiceCategorey serviceCategory);

    public void deactivateServiceCategory(ServiceCategorey serviceCategory);


    public void addService(Service service);

    public void updateService(Service service);

    public void activateService(Service service);

    public void deactivateService(Service service);


    public void addResource(Resouce resource);

    public void deleteResource(Resouce resource);
    
        List<Partener>getPartenerByType(PartnType partnType) ;

    
}