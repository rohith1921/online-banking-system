// message.js — reads ?success= or ?error= in URL and shows a dismissible toast area
(function() {
  function getParam(name) {
    const url = new URL(window.location.href);
    return url.searchParams.get(name);
  }

  const success = getParam('success');
  const error = getParam('error');

  const container = document.getElementById('message');
  if (!container) return;

  if (success) {
    container.innerHTML = `<div class="toast toast-success">${escapeHtml(success)} <button class="toast-close">✕</button></div>`;
  } else if (error) {
    container.innerHTML = `<div class="toast toast-error">${escapeHtml(error)} <button class="toast-close">✕</button></div>`;
  }

  // close handler
  container.addEventListener('click', function(e) {
    if (e.target.classList.contains('toast-close')) {
      e.target.parentElement.remove();
      // remove params from URL (clean)
      const u = new URL(window.location.href);
      u.searchParams.delete('error');
      u.searchParams.delete('success');
      history.replaceState({}, '', u.toString());
    }
  });

  function escapeHtml(s) {
    if (!s) return '';
    return s.replace(/[&<>"']/g, function(m) {
      return ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[m]);
    });
  }
})();
