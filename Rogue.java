public class Rogue extends Adventurer{
  int Apathy, ApathyMax;
  String preferredLanguage;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Rogue(String name, int hp,){
    super(name,hp);
    ApathyMax = 5;
    Apathy = 0;
  }

  public Rogue(String name, int hp){
    this(name,hp);
  }

  public Rogue(String name){
    this(name,75);
  }

  public Rogue(){
    this("Carmack");
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

  /*Deal 2-7 damage to opponent, restores 2 Apathy*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*5)+10;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if Apathy is high enough.
  *Reduces Apathy by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 5){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
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
