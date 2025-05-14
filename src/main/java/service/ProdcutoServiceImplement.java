package service;
import models.Producto;
import java.util.List;
import java.util.Arrays;

public class ProdcutoServiceImplement implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto( 1L, "laptop", "computación", 523.21),
                new Producto(2L, "mouse", "inalambrico", 15.25),
                new Producto(3L, "impresora" , "tinta continua" , 256.25 ));
    }
}
