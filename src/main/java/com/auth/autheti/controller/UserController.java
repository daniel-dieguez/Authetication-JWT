package com.auth.autheti.controller;


import com.auth.autheti.models.RolesModal;
import com.auth.autheti.models.UsersModel;
import com.auth.autheti.models.dtos.UserDTO;
import com.auth.autheti.repository.IRoleRepository;
import com.auth.autheti.service.UserService;
import com.auth.autheti.service.impl.IRoleImpl;
import com.auth.autheti.service.impl.IUserImpl;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserImpl iUserImp;


    @Autowired
    private IRoleImpl iRoleImp;

    private Logger logger = LoggerFactory.getLogger(UsersModel.class);


    @GetMapping("/UserViews")
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






    @DeleteMapping("/DeleteUser/{id_user}")
    public ResponseEntity<?> delete(@PathVariable String id_user){
        Map<String, Object> response = new HashMap<>();
        try{

            UsersModel usersModel = this.iUserImp.findById(id_user);
            if(usersModel == null){
                response.put("mensaje", "El usuario con id".concat(id_user).concat("no existe"));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }else {
                this.iUserImp.delete(usersModel);
                response.put("mensaje","El usuario".concat(id_user).concat("fue eliminado "));
                response.put("listado", usersModel);
                logger.info("El miembro fue eliminada con exito");
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
            }


        }catch (CannotCreateTransactionException e){
        response = this.getTransactionExepcion(response, e);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

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
