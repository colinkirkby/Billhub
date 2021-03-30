package com.Spring.BillHub.Controller;
import com.Spring.BillHub.model.Map_Data_Point;
import com.Spring.BillHub.model.Map_Structure;
import com.Spring.BillHub.service.TokenService;
import org.json.*;
import com.Spring.BillHub.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;


@CrossOrigin(origins = {"http://localhost:3000", "https://billhub-276.herokuapp.com"})
@RestController


@RequestMapping("/api/v1/")
public class MapController {
    @Autowired
    private TokenService tokenService;

    private Map_Structure map_structure = new Map_Structure(0);

    @GetMapping(value = "getpoint")
    public JSONObject getPoint(@RequestParam(name = "latitude") double lat, @RequestParam(name = "longitude") double lng){
        System.out.println("mapping");
        System.out.println("passed values" + lat + lng);
        Map_Data_Point data = map_structure.find_point(lat,lng);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rent",data.getAv_Rent());
        jsonObject.put("cost",data.getAv_Cost());
        jsonObject.put("income",data.getAv_Income());
        jsonObject.put("datapoints",data.getNum_datapoints());
        System.out.println(jsonObject.toJSONString());
        return jsonObject;


    }



}
