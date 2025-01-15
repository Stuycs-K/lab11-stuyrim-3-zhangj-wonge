public class Boss extends Adventurer{
  int Frost, FrostMax;
  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Boss(String name, int hp){
    super(name,hp);
    FrostMax = 5;
    Frost = 0;
  }

  public Boss(String name){
    this(name, 350);
  }

  public Boss(){
    this("Frost Golem");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Frost";
  }

  public int getSpecial(){
    return Frost;
  }

  public void setSpecial(int n){
    Frost= n;
  }

  public int getSpecialMax(){
    return FrostMax;
  }

  public String status(){
    return null;
  }

  /*Deal 2-7 damage to opponent, restores 2 Resolve*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*10+15);
    other.applyDamage(damage);
    restoreSpecial(1);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if Resolve is high enough.
  *Reduces Resolve by 8.
  */
  public String specialAttack(Adventurer other){
    other.setFrozen(true);
    return "froze ally!"; //placeholder
  }

  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return this.getName() +"has freezed your party for one turn!";
  }

  /*Restores 6 special and 1 hp to self.*/
  // gives Golem an extra turn
  public String support(){
    return null;
  }
}
