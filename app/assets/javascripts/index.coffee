$ ->
  $("#latlngform").submit (event) ->
    event.preventDefault()
    lat = $("#lat").val()
    lng = $("#lng").val()
    scale = $("#scale").val()
    console.log(scale)
    url = "/pubs?lat=" + lat + "&lng=" + lng + "&scale=" + scale
    $.get url, (data) ->
      pubs = $("#pubs")
      pubs.empty()
      $.each data.pubs, (index, pub) ->
        pubs.append $("<li>").text pub.name