package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author flo & gabi
 */
public class Player extends LabyrinthCharacter{

    WeaponCardDeck weaponCardDeck = new WeaponCardDeck();
    ShieldCardDeck shieldCardDeck = new ShieldCardDeck();
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;

    private char number;

    private int consecutiveHits = 0;


    public Player(char number, float intelligence, float strength)  {
        super("Player #" + number,intelligence,strength,INITIAL_HEALTH);
        this.number = number;
        weapons = new ArrayList<Weapon>(MAX_WEAPONS);
        shields = new ArrayList<Shield>(MAX_SHIELDS);
        setPos(0,0);
        this.weaponCardDeck = new WeaponCardDeck();
        this.shieldCardDeck = new ShieldCardDeck();

    }

    public Player(Player other) {
        super(other);
        this.number = other.number;
        this.weapons = other.weapons;
        this.shields = other.shields;
        setPos(other.getRow(),other.getCol()); //COpiamos la posicions
        this.weaponCardDeck = other.weaponCardDeck;
        this.shieldCardDeck = other.shieldCardDeck;
    }

    public void resurrect(){
        weapons.clear();
        shields.clear();
        setHealth(INITIAL_HEALTH);
        consecutiveHits = 0;
    }

    public char getNumber() {
        return number;
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

    @Override
    public float attack(){
        return (getStrength()+sumWeapons());
    }

    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }

    public void  receiveReward(){

        weaponCardDeck.addCards();
        for (int i=0; i< weaponCardDeck.getCardDeck().size(); ++i){
            if ( weapons.size() <= Dice.weaponsReward()){
                weapons.add(i,weaponCardDeck.getCardDeck().get(i));
            }
            receiveWeapon(weaponCardDeck.getCardDeck().get(i));

        }
        shieldCardDeck.addCards();
        for (int i=0; i< shieldCardDeck.getCardDeck().size(); ++i){
            if (shields.size() < Dice.shieldsReward()){
                shields.add(i,shieldCardDeck.getCardDeck().get(i));
            }

            receiveShield(shieldCardDeck.getCardDeck().get(i));
        }

        int extraHealth = Dice.healthReward();
        extraHealth += getHealth();
        setHealth(extraHealth);

    }

    @Override
    public String toString(){
        return super.toString() +  "Weapons: " + weapons.toString() + "\n"+ "Shields: " +  shields.toString()+"\n";
    }

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


        //Creamos un nuevo arma
        Weapon newWeapon = weaponCardDeck.nextCard();

        //Guardamos el nuevo arma
        weapons.add(newWeapon);

        return newWeapon;
    }

    private Shield newShield(){
        //Almacenamos la proteccion y el numero de uses del nuevo escudo


        //Creamos un nuevo escudo
        Shield newShield = shieldCardDeck.nextCard();

        //Guardamos el nuevo escudo
        shields.add(newShield);

        return newShield;
    }

   protected float sumWeapons(){
        float sum=0.0f;
        for(Weapon weapon : weapons) {
            sum += weapon.attack();
        }

        return sum;
    }

    protected float sumShields(){
        float sum=0.0f;
        for(Shield shield : shields){
            sum += shield.protect();
        }

        return sum;
    }

    protected float defensiveEnergy(){
        return getIntelligence()+sumShields();
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

    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}