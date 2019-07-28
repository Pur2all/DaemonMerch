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
      if(data.user!='false')
        updateUserInfo(data);
      else
        alert("Error");
    },
    "error":function(error)
    {
      console.log("Error", error);
      alert("Error")
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
      if(data)
        $("#regUsername").css('border', '1px solid red');
      else
        $("#regUsername").css('border', '1px solid green');
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


//Function to retrieve billing addresses async
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


//Function to update a product async
function updateProductAsync(product)
{
  $.ajax({
    "type":"GET",
    "url":"admin/UpdateProduct",
    "data":{product:product},
    "success":function(data)
    {
      if(data)
        updateProductInfo(product);
        else
          alert("Error");
      },
    "error":function(error)
    {
      console.log("Error", error);
      alert("Error")
    }
  });
}


//Function to update a patch async
function updatePatchAsync(patch)
{
  $.ajax({
    "type":"GET",
    "url":"admin/UpdatePatch",
    "data":{patch:patch},
    "success":function(data)
    {
      if(data)
        updatePatchInfo(patch);
      else
        alert("Error");
    },
    "error":function(error)
    {
      console.log("Error", error);
      alert("Error");
    }
  });
}


//Function to update a top async
function updateTopAsync(top)
{
  $.ajax({
    "type":"GET",
    "url":"admin/UpdateTop",
    "data":{top:top},
    "success":function(data)
    {
      if(data)
        updateTopInfo(patch);
      else
        alert("Error");
    },
    "error":function(error)
    {
      console.log("Error", error);
      alert("Error");
    }
  });
}


//Function to delete an artist async
function deleteArtist(id)
{
  $.ajax({
    "type":"GET",
    "url":"admin/DeleteArtist",
    "data":{id:id},
    "success":function(data)
    {
      if(data)
        window.location.replace("/DaemonMerch/Artists");
      else
        alert("Error");
    },
    "error":function(error)
    {
      console.log("Error", error);
      alert("Error");
    }
  });
}


//Function to delete a product async
function deleteArtist(id)
{
  $.ajax({
    "type":"GET",
    "url":"admin/DeleteProduct",
    "data":{id:id},
    "success":function(data)
    {
      if(data)
        window.location.replace("/DaemonMerch/Products&page=1");
      else
        alert("Error");
    },
    "error":function(error)
    {
      console.log("Error", error);
      alert("Error");
    }
  });
}
