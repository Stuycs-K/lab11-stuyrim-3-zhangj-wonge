public class Guardian extends Adventurer{
  int Resolve, ResolveMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Guardian(String name, int hp){
    super(name,hp);
    ResolveMax = 5;
    Resolve = 0;
  }

  public Guardian(String name){
    this(name, 200);
  }

  public Guardian(){
    this("Carmack");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Resolve";
  }

  public int getSpecial(){
    return Resolve;
  }

  public void setSpecial(int n){
    Resolve = n;
  }

  public int getSpecialMax(){
    return ResolveMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 Resolve*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*6)+5;
    other.applyDamage(damage);
    restoreSpecial(1);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if Resolve is high enough.
  *Reduces Resolve by 8.
  */
  public String specialAttack(Adventurer other){
    int damage = (int)(Math.random()*16)+25;
    int accident = (int)(Math.random()*20);
    if(getSpecial() >= 5 && accident < 5){
      setSpecial(getSpecial()-5);
      other.applyDamage(damage);
      return this + " used their shield to ram "+other + ". This threw "+other+" out of this world dealing "+ damage +" points of damage... Oh no! " + this + " accidentally took " + damage+ " while ramming.";
    }
    else if(getSpecial() >= 5 && accident < 5){
      setSpecial(getSpecial()-5);
      other.applyDamage(damage);
      return this + " used their shield to ram "+other + ". This threw "+other+" out of this world dealing "+ damage +" points of damage.";
    }else{
      return "Not enough Resolve to use the ultimate code. Instead "+attack(other);
    }
  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Took damage in place of "+other;
    restoreSpecial(1);
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
