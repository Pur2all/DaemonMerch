function addToCart(element)
{
  $.ajax({
    "type":"GET",
    "url":"auth/AddToCart",
    "data":element,
    "success":function(data)
    {
      var informationDiv="<div> " + element.name + " added to cart </div>"
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

//// TODO: Metti apposto sta roba e scopri come si legge lato server, aggiungi pure ID
function updateUserAsync(user)
{
  $.ajax({
    "type":"POST",
    "url":"auth/UpdateUser",
    "data":user,
    "contentType":"application/json; charset=utf-8",
    "dataType": "json",
    "success":function(data)
    {
      window.location.reload();
    },
    "error":function(error)
    {
      console.log(error);
    }
  });
}
