const seatContainer = document.querySelector(".seat__container")
const modal = document.querySelector(".modal");
const overlay = modal.querySelector(".md_overlay");
const cancleButton = modal.querySelector(".modal_concel");
const okButton = modal.querySelector(".modal_ok");
const modalText = document.querySelector(".modal_text")
const barcode = document.querySelector("#barcode")
let seatNum = 0;


seatContainer.addEventListener('click', (e) => {
    if (e.currentTarget != e.target) {
        modalText.textContent = e.target.textContent + "번 좌석입니다.";
        seatNum = Number(e.target.textContent);
        modal.classList.remove("hidden");
    }
})
cancleButton.addEventListener('click', () => {
    modal.classList.add("hidden");
})

okButton.addEventListener('click', () => {
    // 여기 수정해야 하는 
    const data = {
        seatNum: seatNum.toString(),
        barcode: barcode.value.toString()
    }
    const axiosConfig = {
        headers: {
            "Content-Type": "application/json;charset='utf-8'"
        }
    }
    axios.post("/seating/seatlist/seatInfoSave", data, axiosConfig).then(
        () => {
            modal.classList.add("hidden");
            window.location.href = "/";
        }).catch((e) => {
            console.error(e)
        })
})
