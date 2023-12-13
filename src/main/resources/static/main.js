document.getElementById('addProduct').addEventListener('click', async() => {
    const input = document.getElementById('productText');
    const nameJ = input.value;

    if(nameJ){
        const res = await fetch('/app/products',{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "name": document.getElementById("productText").value})
        });
        input.value='';
        window.location.href = '/';
    }
})
window.onload = async function getAllProducts(){
    const res = await fetch('/app/products/');
    const products = await res.json();
    console.log(products);
    products.forEach(product => ProductsToHTML(product))
}

function ProductsToHTML({id, name, bought}){
    const productList = document.getElementById('products');
    productList.insertAdjacentHTML('beforeend', `
        <div class="form-check" id="product${id}">
            <label class="form-check-label" >
                <input onchange="toggleBoughtProduct(${id})" type="checkbox" class="form-check-input" ${bought && 'checked'}>
                ${name}
            </label>
            <button type="button" onclick="deleteProduct(${id})" class="btn-close" aria-label="Close" style="font-size : 10px"></button>
        </div>
    `);
}

async function deleteProduct(id){
    console.log(id);
    const res = await fetch('/app/products/'+id, {
        method: 'DELETE',
        headers: { "Accept": "application/json" }
    });
    window.location.href = '/';
}

async function toggleBoughtProduct(id){
    const res = await fetch('/app/products/'+id, {
        method: 'PATCH',
        headers:  {
            'Content-Type': 'application/json',
            "Accept": "application/json"
        }
    })
    window.location.href = '/';
}
