public class Grass extends Entity{

//    трава ресурс для травоядных.
    public Grass (){
        setName("Тра");
    }

    @Override
    public String toString() {
        return getName();
    }
}
