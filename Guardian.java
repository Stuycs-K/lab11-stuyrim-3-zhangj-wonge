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
    this("Angel");
  }

  /*The next 5 methods are all required because they are abstract:*/
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

  public String status(){//arraylist for status effects
    String status = "";
    if(this.getFrozen() == true){
      status += "Currently frozen for one turn!\n";
    }else{
      status += "Not frozen.\n";
    }

    if(this.getTaunt() == true){
      status += "Currently taunting! (targeted by enemy)\n";
    }else{
      status += "Not taunting.\n";
    }
    return status;
  }

  /*Deal 2-7 damage to opponent, restores 2 Resolve*/
  public String attack(Adventurer other){
    if(!(this.getFrozen() == true)){
    int damage = (int)(Math.random()*6)+5;
    other.applyDamage(damage);
    restoreSpecial(1);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }
  this.setFrozen(false);
  return "Currently Frozen!";
  }

  /*Deal 25 - 40 damage to opponent, only if Resolve is high enough. 25% chance that the damage inflicts on themself.
  *Reduces Resolve by 5.
  */
  public String specialAttack(Adventurer other){
    if(!(this.getFrozen() == true)){
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
      return "Not enough Resolve to use Special Attack. Instead "+attack(other);
    }
  }
  this.setFrozen(false);
  return "Currently Frozen!";
  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    if(!(this.getFrozen() == true)){
    return "Took damage in place of "+other;
  }
  this.setFrozen(false);
  return "Currently Frozen!";
  }
  /*Restores 6 special and 1 hp to self.*/
  // CURRENTLY PLACEHOLDER
  public String support(){
    if(!(this.getFrozen() == true)){
    int hp = 10;
    setHP(getHP()+hp);
    return this+" shields themself to restores "+restoreSpecial(2)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
  this.setFrozen(false);
  return "Currently Frozen!";
}
}
