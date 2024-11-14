package com.auth.autheti.controller;

import com.auth.autheti.models.RolesModal;
import com.auth.autheti.models.UsersModel;
import com.auth.autheti.models.dtos.UserDTO;
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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/new")
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class UserNewController {


    @Autowired
    private IUserImpl iUserImp;


    @Autowired
    private IRoleImpl iRoleImp;

    private Logger logger = LoggerFactory.getLogger(UsersModel.class);


    @PostMapping("/CreateUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        try {

            RolesModal rolesModal = iRoleImp.findById(userDTO.getRoleId());
            if (rolesModal == null) {
                response.put("mensaje", "El rol especificado no existe.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            if (userDTO.getRoleId() == 1) {
                rolesModal.setRole_name("ADMIN");
            } else if (userDTO.getRoleId() == 2) {
                rolesModal.setRole_name("USER");
            } else {
                response.put("mensaje", "El id_role especificado no es válido.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            UsersModel newUser = new UsersModel();
            newUser.setId_user(UUID.randomUUID().toString());
            newUser.setName(userDTO.getName());
            newUser.setPassword(userDTO.getPassword());
            newUser.setEmail(userDTO.getEmail());
            newUser.setRolesModal(rolesModal);
            this.iUserImp.save(newUser);
            logger.info("se acaba de creaer un nuevo usuario");
            response.put("mensaje", "Unnuevo usuario fue creado con exito ");
            response.put("Usuario Creado", newUser);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (CannotCreateTransactionException e) {
            response.put("mensaje", "Error al crear el usuario: ");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
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
