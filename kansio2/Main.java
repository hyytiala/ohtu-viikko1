/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

/**
 *
 * @author hyytiala
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Database db = new Database("jdbc:sqlite:chat.db");
        ViestiDao vdao = new ViestiDao(db);
        List<Viesti> v = vdao.haeKaikki();

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viestit", paivi(vdao));
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        post("/", (req, res) -> {
            HashMap map = new HashMap<>();
            String nimi = req.queryParams("kayttaja");
            String sisalto = req.queryParams("sisalto");
            vdao.put(nimi, sisalto);
            map.put("viestit", paivi(vdao));

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

    }

    public static void paivita(Database db, List<Viesti> list) throws Exception {
        Database d = new Database("jdbc:sqlite:chat.db");
        ViestiDao vdao = new ViestiDao(d);
        list = vdao.haeKaikki();
        for (Viesti a : list) {
            System.out.println(a.getSisalto());
        }
    }

    public static List<Viesti> paivi(ViestiDao vdao) throws Exception {
        return vdao.haeKaikki();
    }
}
