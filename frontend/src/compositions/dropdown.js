export const dropdown = () => {
  const onClickSelect = (e) => {
    const isActive = e.currentTarget.className.indexOf("active") !== -1;
    if (isActive) {
      e.currentTarget.className = "select";
    } else {
      e.currentTarget.className = "select active";
    }
  }
  
  const onClickOption = (e) => {
    const selectedValue = e.currentTarget.innerHTML;
    document.querySelector(".tab .text").innerHTML = selectedValue;
    console.log(e.currentTarget.value)
  }
  return { onClickSelect, onClickOption }
}
