package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author flo & gabi
 */
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
        this.name = "Player #" + this.number;
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
        return health <= 0.0f;
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);

        if (size > 0 && !contained){
            Directions firsElement = validMoves.get(0);
            return firsElement;
        }
        else{
            return direction;
        }
    }

    public float attack(){
        return (strength+sumWeapons());
    }

    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }

    public void  receiveReward(){
       int wReward = Dice.weaponsReward();
       int sReward = Dice.shieldsReward();

       for(int i=0; i<wReward; ++i){
           Weapon wnew = newWeapon();
           receiveWeapon(wnew);
       }
        for(int i=0; i<sReward; ++i){
            Shield snew = newShield();
            receiveShield(snew);
        }

        int extraHealth = Dice.healthReward();
        this.health += extraHealth;
    }

    public String toString(){
        return "Name: " + name +  ", Intelligence: " + intelligence +  ", Strength: " + strength +
                ", Health: " + health + ", Row: " + row + ", Col: " + col + ", Weapons: " + weapons.toString() + ", Shields: " +  shields.toString()+"\n";
    }

    //Proxima practica
    private void receiveWeapon(Weapon w) {
        for (int i = 0; i < weapons.size(); ++i) {
            Weapon wi = weapons.get(i);
            boolean discard = wi.discard();

            if (discard) {
                weapons.remove(wi);
            }
        }
        int size = weapons.size();
        if (size < MAX_WEAPONS){ weapons.add(w);}

    }

    //Proxima practica
    private void receiveShield(Shield s){
        for (int i=0; i< shields.size();++i){
            Shield si = shields.get(i);
            boolean discard = si.discard();

            if (discard){shields.remove(si);}

        }
        int size = shields.size();

        if (size < MAX_SHIELDS){ shields.add(s);}
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
        float sum=0.0f;
        for(Weapon weapon : weapons) {
            sum += weapon.attack();
        }

        return sum;
    }

    private float sumShields(){
        float sum=0.0f;
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
        boolean lose = false;
        float defense = defensiveEnergy();
        if ( defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }
        else{
            resetHits();
        }

        if(consecutiveHits == HITS2LOSE || dead()){
            resetHits();
            lose=true;
        }
        return lose;
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