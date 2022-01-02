package Will_Hero;

import com.sun.javafx.image.IntPixelGetter;

import java.io.*;

public class Stats {
    private int hours;
    private int moves;
    private int tnt;
    private int coins;
    private int coinChests;
    private int orcs;
    private int coinsSpent;
    private int distance;
    private int platform;
    private int weaponChests;
    private int died;
    private int fallen;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getTnt() {
        return tnt;
    }

    public void setTnt(int tnt) {
        this.tnt = tnt;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoinChests() {
        return coinChests;
    }

    public void setCoinChests(int coinChests) {
        this.coinChests = coinChests;
    }

    public int getOrcs() {
        return orcs;
    }

    public void setOrcs(int orcs) {
        this.orcs = orcs;
    }

    public int getCoinsSpent() {
        return coinsSpent;
    }

    public void setCoinsSpent(int coinsSpent) {
        this.coinsSpent = coinsSpent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getWeaponChests() {
        return weaponChests;
    }

    public void setWeaponChests(int weaponChests) {
        this.weaponChests = weaponChests;
    }

    public int getDied() {
        return died;
    }

    public void setDied(int died) {
        this.died = died;
    }

    public int getFallen() {
        return fallen;
    }

    public void setFallen(int fallen) {
        this.fallen = fallen;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    private int attack;
    Stats() throws FileNotFoundException {
        try {
            int array[] = new int[13];
            File file = new File("src/assets/stats.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String stat;
            int stat_index = 0;
            while ((stat = br.readLine()) != null) {
                System.out.println(stat);
                int index = 0;
                for (int i = 0;i <stat.length();i++){
                    if (stat.charAt(i) == '-'){
                        index = i + 2;
                        i = stat.length();
                    }
                }
                stat = stat.substring(index);
                System.out.println(stat);
                array[stat_index] = Integer.parseInt(stat);
                stat_index++;

            }
            setHours(array[0]);
            setMoves(array[1]);
            setTnt(array[2]);
            setOrcs(array[3]);
            setCoins(array[4]);
            setCoinsSpent(array[5]);
            setPlatform(array[6]);
            setDistance(array[7]);
            setCoinChests(array[8]);
            setWeaponChests(array[9]);
            setAttack(array[10]);
            setDied(array[11]);
            setFallen(array[12]);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    void writeData() throws IOException {
        File file = new File("src/assets/stats.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write("Hours Played - " + Integer.toString(getHours()));
        fileWriter.write("Moves made - " + Integer.toString(getMoves()));
        fileWriter.write("TNT's burst - " + Integer.toString(getTnt()));
        fileWriter.write("Orc's killed - " + Integer.toString(getOrcs()));
        fileWriter.write("Coins collected - " + Integer.toString(getCoins()));
        fileWriter.write("Coins spent - " + Integer.toString(getCoinsSpent()));
        fileWriter.write("Platforms jumped - " + Integer.toString(getPlatform()));
        fileWriter.write("Distance travelled - " + Integer.toString(getDistance()));
        fileWriter.write("Coin Chests opened - " + Integer.toString(getCoinChests()));
        fileWriter.write("Weapon Chests opened - " + Integer.toString(getWeaponChests()));
        fileWriter.write("Times attacked - " + Integer.toString(getAttack()));
        fileWriter.write("Times died - " + Integer.toString(getDied()));
        fileWriter.write("Times fallen to death - " + Integer.toString(getFallen()));

    }
}
