function searchProducts() {
    var searchInput = document.getElementById('searchInput').value;
    window.location.href = '/products?search=' + searchInput;
}