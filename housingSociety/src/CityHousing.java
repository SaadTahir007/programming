public class CityHousing {

    private String cityName;
    private HousingSociety[] societies;

    public CityHousing(String name) {
        this.cityName = name;
        this.societies = new HousingSociety[2];
        societies[0] = new HousingSociety("LDA Avenue 1");
        societies[1] = new HousingSociety("LDA Avenue 2");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("City: " + cityName + "\n\n");
        for (int i = 0; i < societies.length; i++) {
            sb.append(societies[i].toString() + "\n");
        }
        return sb.toString();
    }
}
