package co.unicauca.microkernel.plugins.brasil;

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.entities.Product;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;

public class BrasilDeliveryPlugin implements IDeliveryPlugin {

    /**
     * El cálculo de Colombia es una mezcla de peso y distancia.
     *
     * @param delivery envío
     * @return costo del envío
     */
    public double calculateCost(Delivery delivery) {

        Product product = delivery.getProduct();
        double weight = product.getWeight();
        double distance = delivery.getDistance();

        double cost;

        if (weight > 5) {

            cost = 2 * distance;

        } else if (weight <= 5 && weight > 1) {

            cost = distance * 1.5;

        } else {
            cost = 50;
        }

        return cost;

    }
}
