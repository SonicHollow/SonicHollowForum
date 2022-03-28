$('.select-container > .select-placeholder').click(function() {
    $('.select-container ul').toggleClass('active');
});
$('.select-container ul > li').click(function() {
    $('.select-container .select-placeholder').text($(this).text());
    $('.select-container ul').toggleClass('active');
    $('.select-container .select-placeholder').toggleClass('active');
});