function addToCart(id)
{
  $.ajax({
    "type":"GET",
    "url":"auth/AddToCart",
    "data":{productId:id},
    "success":function(data)
    {
      var informationDiv="<div> " + data.name + " added to cart </div>"
      $(".index-header").append(informationDiv);
      $(".index-header").remove(informationDiv);
    }
  });
}

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

function updateUserAsync(user)
{
  console.log("Ue fra", user);
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
