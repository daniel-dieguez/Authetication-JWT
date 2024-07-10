package com.auth.autheti.controller;

import com.auth.autheti.models.RolesModal;
import com.auth.autheti.models.UsersModel;
import com.auth.autheti.service.RolesService;
import com.auth.autheti.service.impl.IRoleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Roles")
@CrossOrigin(origins = "*")
public class RolesController {

    @Autowired
    private IRoleImpl iRoleImp;


    private Logger logger = LoggerFactory.getLogger(UsersModel.class);

    @GetMapping("/ViewRoles")
    public ResponseEntity<?> viewRoles(){
        Map<String, Object> response = new HashMap<>();
        try{
            List<RolesModal> rolesModal = this.iRoleImp.findAll();
            logger.info("Se a realizado la consulta");
            return new ResponseEntity<List<RolesModal>>(rolesModal, HttpStatus.OK);


        }catch(CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            response = this.getDataAccessException(response, e);
            return  new ResponseEntity<Map<String, Object>>(HttpStatus.SERVICE_UNAVAILABLE);

        }
    }




    private Map<String, Object> getTransactionExepcion(Map<String,Object> response, CannotCreateTransactionException e){
        logger.error("Error al momento de conectarse a la base de datos");
        response.put("mensaje", "error al momento de contectarse a la");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;
    }

    private Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e){
        logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
        response.put("mensaje", "Error al momenot de ejecutar la consulta a la base de datos");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;

    }

}
