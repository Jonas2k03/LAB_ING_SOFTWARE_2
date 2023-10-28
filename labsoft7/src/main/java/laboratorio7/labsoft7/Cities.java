package laboratorio7.labsoft7;
import Model.LeafPack;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Carlos Fernandez Cuetia (jcfernandezc@unicauca.edu.co)
 * @author Jonathan Felipe Hurtado Diaz (jfhurtadod@unicauca.edu.co)
 * 
 */

public class Cities {

    public LeafPack NewYork() {
        List<String> NewYork_CityTour = fill_cityTour("NYCT001", "Explorando Manhattan", "Descripción: Recorre los lugares icónicos de Manhattan, incluyendo Times Square, Central Park y el Empire State Building.", "Recomendaciones: No olvides tomar fotos de los rascacielos y probar una rebanada de pizza en una pizzería local.", "4 horas");
        LeafPack city = new LeafPack("23 de mayo de 2023", "Hotel: Grand Central Hotel", NewYork_CityTour, "Fit", "Check-In: 24 de mayo de 2023", "Check-Out: 28 de mayo de 2023", "New York");
        return city;
    }

    public LeafPack RioDeJaneiro() {
        List<String> RioDeJaneiro_CityTour = fill_cityTour("RJCT001", "Cristo Redentor y Pan de Azúcar", "Visita dos de los sitios más emblemáticos de Río de Janeiro: el Cristo Redentor y el Pan de Azúcar.", "Recomendaciones: Lleva protector solar y ropa cómoda para caminar. Disfruta de la vista panorámica de la ciudad.", "6 horas");
        LeafPack city = new LeafPack("10 de julio de 2023", "Hotel: Copacabana Beach Resort", RioDeJaneiro_CityTour, "Plan estándar", "Check-In: 11 de julio de 2023", "Check-Out: 15 de julio de 2023", "Río de Janeiro");
        return city;
    }

    public LeafPack Kyoto() {
        List<String> Kyoto_CityTour = fill_cityTour("KYCT001", "Templos Históricos de Kioto", "Explora los antiguos templos de Kioto, incluyendo el Templo Kinkaku-ji y el Templo Ginkaku-ji.", "Recomendaciones: Viste apropiadamente y respeta las tradiciones locales. No te pierdas la ceremonia del té.", "5 horas");
        LeafPack city = new LeafPack("5 de septiembre de 2023", "Hotel: Zen Garden Ryokan", Kyoto_CityTour, "Plan tradicional", "Check-In: 6 de septiembre de 2023", "Check-Out: 10 de septiembre de 2023", "Kioto");
        return city;
    }

    public LeafPack Mumbai() {
        List<String> Mumbai_CityTour = fill_cityTour("MBCT001", "Recorrido por la Puerta de la India", "Descubre la historia detrás de la Puerta de la India y explora el mercado de Colaba.", "Recomendaciones: Prueba la comida callejera local y disfruta de la mezcla de culturas en Mumbai.", "3 horas");
        LeafPack city = new LeafPack("17 de noviembre de 2023", "Hotel: Taj Mahal Palace", Mumbai_CityTour, "Plan de lujo", "Check-In: 18 de noviembre de 2023", "Check-Out: 22 de noviembre de 2023", "Mumbai");
        return city;
    }

    public LeafPack Paris() {
        List<String> Paris_CityTour = fill_cityTour("PACT001", "Paseo por el Louvre y el Sena", "Explora el famoso Museo del Louvre y realiza un crucero por el río Sena.", "Recomendaciones: Compra tus boletos en línea para evitar filas y lleva una chaqueta ligera para el crucero.", "5 horas");
        LeafPack city = new LeafPack("2 de febrero de 2023", "Hotel: Le Ritz Paris", Paris_CityTour, "Plan de lujo", "Check-In: 3 de febrero de 2023", "Check-Out: 7 de febrero de 2023", "París");
        return city;
    }

    public LeafPack Rome() {
        List<String> Rome_CityTour = fill_cityTour("RMCT001", "Explorando el Coliseo y el Foro Romano", "Descubre la historia antigua de Roma visitando el Coliseo y el Foro Romano.", "Recomendaciones: Lleva calzado cómodo y una guía para aprender sobre la historia del lugar.", "4 horas");
        LeafPack city = new LeafPack("14 de mayo de 2023", "Hotel: Hotel Eden", Rome_CityTour, "Plan estándar", "Check-In: 15 de mayo de 2023", "Check-Out: 19 de mayo de 2023", "Roma");
        return city;
    }

    public LeafPack Cairo() {
        List<String> Cairo_CityTour = fill_cityTour("EGCT001", "Exploración de las Pirámides de Giza", "Maravíllate con las Pirámides de Giza y la Esfinge en este tour histórico.", "Recomendaciones: Lleva agua y protector solar. Contrata un guía para obtener información detallada.", "6 horas");
        LeafPack city = new LeafPack("20 de agosto de 2023", "Hotel: Mena House Hotel", Cairo_CityTour, "Plan estándar", "Check-In: 21 de agosto de 2023", "Check-Out: 25 de agosto de 2023", "El Cairo");
        return city;
    }

    public LeafPack CapeTown() {
        List<String> CapeTown_CityTour = fill_cityTour("CACT001", "Recorrido por la Península del Cabo", "Descubre la belleza natural de la Península del Cabo, incluyendo el Cabo de Buena Esperanza.", "Recomendaciones: Lleva una cámara y disfruta de la fauna local. Haz una parada en Boulders Beach para ver pingüinos.", "8 horas");
        LeafPack city = new LeafPack("8 de abril de 2023", "Hotel: Table Bay Hotel", CapeTown_CityTour, "Plan de aventura", "Check-In: 9 de abril de 2023", "Check-Out: 13 de abril de 2023", "Ciudad del Cabo");
        return city;
    }

    public LeafPack Sydney() {
        List<String> Sydney_CityTour = fill_cityTour("SYDCT001", "Tour de la Ópera de Sídney y el Puente del Puerto", "Visita la icónica Ópera de Sídney y camina sobre el Puente del Puerto.", "Recomendaciones: Compra tus boletos con anticipación. Disfruta de las vistas panorámicas desde el puente.", "4 horas");
        LeafPack city = new LeafPack("12 de octubre de 2023", "Hotel: Sydney Harbour Marriott Hotel at Circular Quay", Sydney_CityTour, "Plan estándar", "Check-In: 13 de octubre de 2023", "Check-Out: 17 de octubre de 2023", "Sídney");
        return city;
    }

    public LeafPack Auckland() {
        List<String> Auckland_CityTour = fill_cityTour("AKLCT001", "Explorando la Isla Norte de Nueva Zelanda", "Descubre la belleza natural de la Isla Norte, incluyendo la región de Rotorua y la Cueva de Waitomo.", "Recomendaciones: Prepárate para actividades al aire libre y aventuras. No te pierdas la experiencia de las luciérnagas en la cueva.", "5 días");
        LeafPack city = new LeafPack("30 de junio de 2023", "Hotel: SkyCity Grand Hotel", Auckland_CityTour, "Plan de aventura", "Check-In: 1 de julio de 2023", "Check-Out: 5 de julio de 2023", "Auckland");
        return city;
    }
    
     

    public List<String> fill_cityTour(String ID, String name, String relevance, String recommendations, String duration) {
        List<String> CityTour = new ArrayList<>();
        CityTour.add(ID);
        CityTour.add(name);
        CityTour.add(relevance);
        CityTour.add(recommendations);
        CityTour.add(duration);

        return CityTour;
    }
    
   
}
