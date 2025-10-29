public class HousingSociety {

    private String societyName;
    private Block[] blocks;

    public HousingSociety(String name) {
        this.societyName = name;
        this.blocks = new Block[3]; 
        blocks[0] = new Block("Block A", 5);
        blocks[1] = new Block("Block B", 5);
        blocks[2] = new Block("Block C", 5);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Society: " + societyName + "\n");
        for (int i = 0; i < blocks.length; i++) {
            sb.append(blocks[i].toString() + "\n");
        }
        return sb.toString();
    }
}
