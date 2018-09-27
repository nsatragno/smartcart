package ar.com.smartcart.smartcart.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.smartcart.smartcart.modelo.ProductoEnLista;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ProductoContent {

    public static final List<ProductoEnLista> ITEMS = new ArrayList<ProductoEnLista>();

    public static final Map<Long, ProductoEnLista> ITEM_MAP = new HashMap<Long, ProductoEnLista>();

    private static final int COUNT = 25;

    private static void addItem(ProductoEnLista item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getProducto().getId(), item);
    }
}
