<div class="header">
       <div class="title">Vegan Cakes</div>
       <div class="nav">
           <a href="explore.html">Explore</a>
           <a href="mycart.html">My Cart</a>
           <c:if test="${not empty firstName}">
               <p class="user-greeting">Howdy ${firstName}</p>
           </c:if>
       </div>
</div>