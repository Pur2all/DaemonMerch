function addToCart(element)
{
  $.ajax({
    "type":"GET",
    "url":"auth/AddToCart",
    "data":element,
    "success":function(data)
    {
      $(".index-header").append("<div> " + $.parseJSON(element).name + " added to cart </div>");
    }
  });
}

function insertProduct(element)
{
  $.ajax({
    "type":"GET",
    "url":"admin/InsertProduct",
    "data":element,
    "success":function(data)
    {
      $(".index-header").append("<div> " + $.parseJSON(element).name + " insert in products </div>");
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
        $("#artists").append(option)
      });
    }
  });
}
