<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dungeons &amp; Dragons 5th Edtion Character Sheet</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap-theme.min.css">
<script src="/resources/js/jquery-2.1.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script>
$('#characterTab a').click(function (e) {
	console('test')
	  e.preventDefault()
	  $(this).tab('show')
	})
</script>
</head>
<body>
<!-- Nav Tabs -->
<ul class="nav nav-tabs" role="tablist" id="characterTab">
  <li class="active"><a href="#character" role="tab" data-toggle="tab">Character</a></li>
  <li><a href="#equipment" role="tab" data-toggle="tab">Equipment</a></li>
  <li><a href="#spell" role="tab" data-toggle="tab">Spell</a></li>
  <li><a href="#profile" role="tab" data-toggle="tab">Profile</a></li>
</ul>

<div class="container">
<!-- Tab panes -->
<div class="tab-content">
  <div class="tab-pane active" id="character">
    <div class="row">
      <div class="col-md-4">${character.name }</div>
      <div class="col-md-8">
        <div class="row">
          <div class="col-md-6">Class : ${character.characterClass }</div>
          <div class="col-md-3">Level : ${character.level }</div>
          <div class="col-md-3">XP : ${character.xp }</div>      
        </div>
        <div class="row">
          <div class="col-md-6">Race : ${character.race }</div>
          <div class="col-md-6">Alignment : ${character.alignment }</div>         
        </div>
      </div>   
    </div>
    <div class="row">
      <div class="col-md-3">
        <div class="row">
          Proficiency Bonus : ${character.proficiencyBonus }
        </div>
        <div class="row">
          <table class="table table-condensed">
            <caption>Ability</caption>
            <thead>
              <tr>
                <th>Ability</th><th>Score</th><th>Modifier</th>
              </tr>
            <thead>
            <tbody>
              <c:forEach var="ability" items="${character.abilities }">
              <tr>
                <td>${ability.type }</td><td>${ability.score }</td><td><b>${ability.modifier }</b></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        
        <div class="row">
          <table class="table table-condensed">
            <caption>Saving Throws</caption> 
            <tbody>
              <c:forEach var="savingThrow" items="${character.savingThrows }">
              <tr>
                <td>${savingThrow.abilityType }</td>
                <td><c:choose><c:when test="${savingThrow.proficiency }"><img src="/resources/images/checkbox_yes.png" /></c:when><c:otherwise><img src="/resources/images/checkbox_no.png" /></c:otherwise></c:choose></td>
                <td><b>${savingThrow.value }</b></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        
        <div class="row">
          <table class="table table-condensed">
            <caption>Skills</caption> 
            <tbody>
              <c:forEach var="skill" items="${character.skills }">
              <tr>
                <td>${skill.name } (${skill.abilityType.shorten})</td>
                <td><c:choose><c:when test="${skill.proficiency }"><img src="/resources/images/checkbox_yes.png" /></c:when><c:otherwise><img src="/resources/images/checkbox_no.png" /></c:otherwise></c:choose></td>
                <td><b>${skill.value }</b></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <div class="col-md-1"></div>
      <div class="col-md-3">
        <div class="row">
        AC : ${character.armorClass }
        </div>
        <div class="row">
        Initiative : ${character.initiative }
        </div>
        <div class="row">
        Speed : ${character.speed } feet
        </div>
        <div class="row">
        HP Max : ${character.hpMax }
        </div>
        <div class="row">
        HP Current : ${character.hpCurrent }
        </div>
        <div class="row">
          <div class="row">
          <table class="table table-condensed">
            <caption>Atacks</caption>
            <thead>
              <tr>
                <th>Name</th><th>Attack Bonus</th><th>Damage/Type</th>
              </tr>
            <thead>
            <tbody>
              <c:forEach var="weapon" items="${character.weapons }">
              <tr>
                <td>${weapon.name }</td>
                <td>${weapon.attackBonus }</td>
                <td><b>${weapon.damage } (${weapon.damageType })</b></td>
              </tr>
              </c:forEach>
            </tbody>

          </table>
        </div>
        </div>
      </div>
      <div class="col-md-4">
        <h4>Features</h4>
      </div>    
      <div class="col-md-1"></div>
    </div>
  </div>
  <div class="tab-pane" id="equipment">...</div>
  <div class="tab-pane" id="spell">...</div>
  <div class="tab-pane" id="profile">...</div>
</div>

</div>
</body>
</html>