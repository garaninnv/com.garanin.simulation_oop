public class Rock extends Entity {
//    камень/ статичные объекты

    public Rock() {
        setName("Кам");
    }

    @Override
    public String toString() {
        return getName();
    }
}
