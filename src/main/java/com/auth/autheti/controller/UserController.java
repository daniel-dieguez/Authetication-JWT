package com.auth.autheti.controller;


import com.auth.autheti.models.RolesModal;
import com.auth.autheti.models.UsersModel;
import com.auth.autheti.repository.IRoleRepository;
import com.auth.autheti.service.UserService;
import com.auth.autheti.service.impl.IRoleImpl;
import com.auth.autheti.service.impl.IUserImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserImpl iUserImp;


    @Autowired
    private IRoleImpl iRole;

    private Logger logger = LoggerFactory.getLogger(UsersModel.class);

    @GetMapping("/userViews")
    public ResponseEntity<?> listUser(){
        Map<String, Object> response = new HashMap<>();


        this.logger.debug("iniciando consulta");
        try {
            List<UsersModel> usersModel = this.iUserImp.findAll();
            logger.info("Se a realizado la peticion para burcar usuarios");
            return new ResponseEntity<List<UsersModel>>(usersModel, HttpStatus.OK);

        }catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
        }


    @PostMapping("/CreateUser")
    public ResponseEntity<?>createUser(@Valid @RequestBody UsersModel value, BindingResult result){
        Map<String, Object> response = new HashMap<>();

        try{
            UsersModel modelUser;
            RolesModal rolesModal = new RolesModal();

            if(modelUser.setRolesModal() == 1){
                this.iRole.findById(1);
                rolesModal.setRole_name("ADMIN");
            } else if (modelUser.setRolesModal() == 2) {
                this.iRole.findById(2);
                rolesModal.setRole_name("USER");

            }

            UsersModel usersModel = new UsersModel();
        usersModel.setId_user(UUID.randomUUID().toString());
        usersModel.setName(value.getName());
        usersModel.setPassword(value.getEmail());
        usersModel.setEmail(value.getEmail());
        usersModel.setRolesModal(value.getRolesModal());
        this.iUserImp.save(usersModel);
        logger.info("se acaba de creaer un nuevo usuario");
        response.put("mensaje", "Unnuevo usuario fue creado con exito ");
        response.put("Usuario Creado", usersModel);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatu s.CREATED);
        }catch (CannotCreateTransactionException e) {
            response.put("mensaje", "Error al crear el usuario: " );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }




    private Map<String, Object> getTransactionExepcion(Map<String,Object> response, CannotCreateTransactionException e){
        logger.error("Error al momento de conectarse a la base de datos");
        response.put("mensajee", "error al moneotno de contectarse a la");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;
    }

    private Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e){
        logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
        response.put("mensaje", "Error al momenot de ejecutar ola consulta a la base de datos");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;

    }
}
