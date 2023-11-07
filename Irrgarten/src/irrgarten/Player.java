package irrgarten;

import java.util.ArrayList;

public class Player {

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    private static final int MAX_WEAPONS = 5;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;

    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;


    public Player(char number, float intelligence, float strength)  {
        this.number = number;
        this.name = "Player # " + number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        weapons = new ArrayList<Weapon>(MAX_WEAPONS);
        shields = new ArrayList<Shield>(MAX_SHIELDS);
        row = 0;
        col = 0;
    }

    public void resurrect(){
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getNumber() {
        return number;
    }

    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }

    public boolean dead(){
        return health <= 0;
    }

    //Proxima practica
    public Directions move(Directions direction, Directions[] validMoves){
        throw new UnsupportedOperationException();
    }

    public float attack(){
        return strength+sumWeapons();
    }

    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }

    public void  receiveReward(){
        throw new UnsupportedOperationException();
    }

    public String toString(){
        return "Player [name: " + name +  ", intelligence: " + intelligence +  ", strength: " + strength +
                ", health: " + health + ", row: " + row + ", col: " + col + ", weapons: " + weapons.toString() + ", shields: " +  shields.toString()+"]";
    }

    //Proxima practica
    private void receiveWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }

    //Proxima practica
    private void receiveShield(Shield s){
        throw new UnsupportedOperationException();
    }

    private Weapon newWeapon(){
        //Almacenamos el poder y el numero de uses del nuevo arma
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();

        //Creamos un nuevo arma
        Weapon newWeapon = new Weapon(power,uses);

        //Guardamos el nuevo arma
        weapons.add(newWeapon);

        return newWeapon;
    }

    private Shield newShield(){
        //Almacenamos la proteccion y el numero de uses del nuevo escudo
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();

        //Creamos un nuevo escudo
        Shield newShield = new Shield(protection,uses);

        //Guardamos el nuevo escudo
        shields.add(newShield);

        return newShield;
    }

    private float sumWeapons(){
        float sum=0;
        for(Weapon weapon : weapons) {
            sum += weapon.attack();
        }

        return sum;
    }

    private float sumShields(){
        float sum=0;
        for(Shield shield : shields){
            sum += shield.protect();
        }

        return sum;
    }

    private float defensiveEnergy(){
        return intelligence+sumShields();
    }

    //Proxima practica
    private boolean manageHit(float receivedAttack){
        throw new UnsupportedOperationException();
    }

    private void resetHits(){
        consecutiveHits = 0;
    }

    private void gotWounded(){
        health--;
    }

    private void incConsecutiveHits(){
        consecutiveHits++;
    }






}