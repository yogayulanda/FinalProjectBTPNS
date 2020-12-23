package org.example.restapi.controller;

import com.google.gson.Gson;
import org.example.database.model.Register;
import org.example.database.model.User;
import org.example.restapi.rabbitmq.ApiReceiver;
import org.example.restapi.rabbitmq.ApiSender;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    public final ApiReceiver receiver = new ApiReceiver();

    // -------------------Register Nasabah-------------------------------------------
    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody Register register) {
        try {
            ApiSender.addNasabah(new Gson().toJson(register));
            receiver.receiveFromDatabase();
            JSONObject object = new JSONObject();
            object.put("response",200);
            object.put("status","Success");
            object.put("message","Success Add Data Nasabah");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Add Nasabah");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }
    // -------------------Login User-------------------------------------------
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            ApiSender.login(new Gson().toJson(user));
            return new ResponseEntity<>(receiver.loginApiRes(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Login, Please Check Username or Password!!!");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

    // -------------------Logout User-------------------------------------------
    @RequestMapping(value = "/logout/", method = RequestMethod.PUT)
    public ResponseEntity<?> logoutUser(@RequestBody User user) {
        try {
            ApiSender.logout(new Gson().toJson(user));
            return new ResponseEntity<>(receiver.logoutApiRes(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Logout!!!");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

    // -------------------Cek Saldo-------------------------------------------

    @RequestMapping(value = "/checksaldo/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> checkSaldo(@PathVariable("username") String username) {
        try {
            ApiSender.checkSaldo(username);
            return new ResponseEntity<>(receiver.checkSaldo(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Login, Please Check Username or Password!!!");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }
}