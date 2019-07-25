//Function for add a product to cart
function addToCart(id)
{
  $.ajax({
    "type":"GET",
    "url":"auth/AddToCart",
    "data":{productId:id},
    "success":function(data)
    {
      // TODO: Animare sta roba
      var informationDiv="<div> " + data.name + " added to cart </div>"
      $(".index-header").append(informationDiv);
      $(".index-header").remove(informationDiv);
    }
  });
}

//Function to retrieve artists async
function retrieveArtists()
{
  $.ajax({
    "type":"GET",
    "url":"RetrieveArtistsAsync",
    "success":function(data)
    {
      var arrayOfArtists=data;
      $.each(arrayOfArtists, function(index, val)
      {
        var option=new Option(val.name, val.id);
        $(option).html(val.name);
        $("#artists").append(option);
      });
    }
  });
}

//Function for update user async
function updateUserAsync(user)
{
  $.ajax({
    "type":"POST",
    "url":"auth/UpdateUser",
    "data":{user:user},
    "success":function(data)
    {
      // TODO: Anzichè fare questo cambia i valori nel form normale
      //window.location.reload();
    },
    "error":function(error)
    {
      console.log("Error", error);
    }
  });
}

//Function for check if a username is already in use
function checkUsername(username)
{
  $.ajax({
    "url":"CheckUsername",
    "type":"GET",
    "data":{username:username},
    "success":function(data)
    {
/*      if(data)
        // TODO: Animare il campo di input per l'username rendendolo rosso e dicendo che l'utente è già preso
      else
*/        // TODO: Animarlo come sopra ma col verde dicendo che è ok l'username
    }
  });
}

//Function to add a product to wishlist
function addToWishlist(id)
{
  $.ajax({
    "type":"GET",
    "url":"auth/AddToWishlist",
    "data":{productId:id},
    "success":function(data)
    {
      // TODO: Animare sta roba
      var informationDiv="<div> " + data.name + " added to wishlist </div>"
      $(".index-header").append(informationDiv);
      $(".index-header").remove(informationDiv);
    }
  });
}


//Function to retrieve credit cards async
function retrieveCreditCardsAsync(id)
{
  $.ajax({
    "type":"GET",
    "url":"auth/CreditCards",
    "success":function(data)
    {
      visualizeCreditCards(data); // TODO: implementare la funzione per visualizzarli in js
    }
  });
}


//Function to retrieve credit cards async
function retrieveBillingAddressesAsync(id)
{
  $.ajax({
    "type":"GET",
    "url":"auth/BillingAddressesAsync",
    "success":function(data)
    {
      visualizeBillingAddresses(data); // TODO: implementare la funzione per visualizzarli in js
    }
  });
}


//Function to delete a product from the wishlist
function deleteProductFromWishlist(wishlistProduct)
{
  $.ajax({
    "type":"GET",
    "url":"auth/DeleteProductInWishlist",
    "data":{wishlistProduct:wishlistProduct},
    "success":function(data)
    {
      if(data)
        addToCart(wishlistProduct.id);
      else
        console.log("No");
    }
  });
}


//Function to delete a product from the cart
function deleteProductFromCart(index)
{
  $.ajax({
    "type":"GET",
    "url":"auth/RemoveFromCart",
    "data":{index:index},
    "success":function(data)
    {
      // TODO: Animare sta roba
      var informationDiv="<div> " + data.name + " removed from cart </div>"
      $(".index-header").append(informationDiv);
      $(".index-header").remove(informationDiv);
    }
  });
}
