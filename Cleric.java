public class Cleric extends Adventurer{
  int Grace, GraceMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Cleric(String name, int hp){
    super(name,hp);
    GraceMax = 5;
    Grace = 0;
  }

  public Cleric(String name){
    this(name, 150);
  }

  public Cleric(){
    this("Carmack");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Grace";
  }

  public int getSpecial(){
    return Grace;
  }

  public void setSpecial(int n){
    Grace = n;
  }

  public int getSpecialMax(){
    return GraceMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 Grace*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*5)+3;
    other.applyDamage(damage);
    restoreSpecial(1);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if Grace is high enough.
  *Reduces Grace by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 5){
      setSpecial(getSpecial()-5);
      other.setHP(getmaxHP());
      return this + " always has " + other + "'s back. " + this + " healed " + other + " back to full health";
    }else{
      return "Not enough Grace to use the ultimate code. Instead "+attack(other);
    }
  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    int startHealth = other.getHP();
    int HPneeded = other.getmaxHP() - startHealth;
    int endHP = startHealth + HPneeded;
    other.setHP(endHP);
    return this + " healed " + other + " adding " + HPneeded + " to their HP. " + other + " is now at " + endHP + " HP.";
  }
  public String support(){
    int startHealth = this.getHP();
    int HPneeded = this.getmaxHP() - startHealth;
    int endHP = startHealth + HPneeded;
    this.setHP(endHP);
    return this + " healed themselves adding " + HPneeded + " to their HP. " + this + " is now at " + endHP + " HP.";
  }
}