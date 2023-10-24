<div class="header">
       <div class="title">Vegan Cakes</div>
       <div class="nav">
           <a href="explore.jsp">Explore</a>
           <a href="cart.jsp">My Cart <span class="cart_quantity">${cart_list.size()}</span></a>
           <c:if test="${not empty firstName}">
               <p class="user-greeting">Howdy ${firstName}</p>
           </c:if>
       </div>
</div>