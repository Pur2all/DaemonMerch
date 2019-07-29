//Function for add a product to cart
function addToCart(id)
{
  $.ajax({
    "type":"GET",
    "url":"auth/AddToCart",
    "data":{productId:id},
    "success":function(data)
    {
      	addedToCart(data.name);
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


//Function to retrieve credit cards async
function retrieveCreditCards()
{
  $.ajax({
    "type":"GET",
    "url":"/auth/CreditCards",
    "success":function(data)
    {
      var arrayOfCreditCards=data;
      $.each(arrayOfCreditCards, function(index, val)
      {
        // TODO: vedere che fare
      });
    }
  });
}


//Function to retrieve billing addresses async
function retrieveBillingAddresses()
{
  $.ajax({
    "type":"GET",
    "url":"/auth/BillingAddresses",
    "success":function(data)
    {
      var arrayOfBillingAddresses=data;
      $.each(arrayOfBillingAddresses, function(index, val)
      {
        //TODO vedere che fare
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
function deleteProductFromCart(index, total, price)
{
  $.ajax({
    "type":"GET",
    "url":"/DaemonMerch/auth/RemoveFromCart",
    "data":{index:index},
    "success":function(data)
    {
      removeFromCart(index, total, price);
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


//Function to know currency value of base to symbol
function changeAsync(base, symbol)
{
  var currencyValue;

  $.ajax({
    "type":"GET",
    "url":"https://api.exchangeratesapi.io/latest?base=" + base + "&symbols=" + symbol,
    "success":function(data)
    {
      currencyValue=data.rates[0];
    },
    "error":function(error)
    {
      console.log("Error", error);
      alert("Error in currency exchange");
    }
  });

  return currencyValue;
}


//Function to add credit card
function addCreditCard()
{
  var creditCard={
    number:$("#cardNumber").val(),
    expireDate:$("#cardDate").val(),
    cvv:$("#cardCVV").val()
  };

  $.ajax({
    "type":"GET",
    "url":"auth/InsertCreditCard",
    "data":{creditCard:JSON.stringify(creditCard)},
    "success":function(data)
    {
      alert("Inserted");
    }
  });

  event.preventDefault();
}


//Function to add billing address
function addBillingAddress()
{
  var billingAddress={
    state:$("#state").val(),
    city:$("#city").val(),
    street:$("#street").val(),
    district:$("#district").val(),
    houseNumber:$("#houseNumber").val()
  };

  $.ajax({
    "type":"GET",
    "url":"auth/InsertBillingAddress",
    "data":{billingAddress:JSON.stringify(billingAddress)},
    "success":function(data)
    {
      alert("Inserted");
    }
  });
}
