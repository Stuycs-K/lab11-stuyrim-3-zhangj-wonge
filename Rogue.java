public class Rogue extends Adventurer{
  int Apathy, ApathyMax, DamageMin, DamageMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Rogue(String name, int hp, int DamageMin, int DamageMax){
    super(name,hp);
    ApathyMax = 5;
    Apathy = 0;
    DamageMin = 10;
    DamageMax = 15;
  }

  public Rogue(String name, int hp){
    this(name, hp, 10, 15);
  }

  public Rogue(String name){
    this(name,75);
  }

  public Rogue(){
    this("Theo");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Apathy";
  }

  public int getSpecial(){
    return Apathy;
  }

  public void setSpecial(int n){
    Apathy = n;
  }

  public int getSpecialMax(){
    return ApathyMax;
  }

  public void setDamageMin(int n){
    DamageMin = n;
  }

  public int getDamageMin(){
    return DamageMin;
  }

  public void setDamageMax(int n){
    DamageMax = n;
  }

  public int getDamageMax(){
    return DamageMax;
  }
  /*Deal 2-7 damage to opponent, restores 2 Apathy*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()* (DamageMax - DamageMin + 1))+DamageMin;
    other.applyDamage(damage);
    setSpecial(getSpecial() + 1);
    setDamageMax(getDamageMax() + 1);
    setDamageMin(getDamageMin() + 1);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if Apathy is high enough.
  *Reduces Apathy by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 5){
      setSpecial(getSpecial()-5);
      int damage = 75;
      other.applyDamage(damage);
      return this + " hit a critical spot with their dagger. "+
      " This blew "+other+" out of this world dealing "+ damage +" points of damage.";
    }else{
      return "Not enough Apathy to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
